pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Start HTML server') {
            steps {
                sh 'python3 -m http.server 8000 --directory test-page > server.log 2>&1 &'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            sh 'pkill -f "python3 -m http.server 8000" || true'
        }
    }
}