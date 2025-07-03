pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/ArshadDev/demo.git'
        BRANCH = 'master'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: "${BRANCH}", url: "${REPO_URL}"
            }
        }

        stage('Build Application') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Kill and Restart Spring Boot App') {
	steps {
		bat '''
		@echo off
		echo Looking for process on port 9190...
		for /f "tokens=5" %%a in ('netstat -aon ^| findstr :9190 ^| findstr LISTENING') do (
			echo Killing process ID %%a
			taskkill /F /PID %%a
		)
		
		echo Starting Spring Boot application...
		cd path\\to\\your\\spring-boot-app
		start "" java -jar demo-0.0.1-SNAPSHOT.jar
		'''
	}
}
    }

    post {
        success {
            echo "✅ Application is running on http://localhost:9190"
        }
        failure {
            echo "❌ Pipeline failed!"
        }
    }
}
