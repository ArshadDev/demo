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

        stage('Run Application') {
            steps {
                script {
                    // Kill any previous Java processes running the Spring Boot app
                    bat '''
                    for /f "tokens=5" %%a in ('netstat -aon ^| findstr :9190') do taskkill /F /PID %%a
                    '''

                    // Run the application in the background
                    bat '''
                    start "" java -jar target\\demo-0.0.1-SNAPSHOT.jar > app.log 2>&1
                    '''
                }
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
