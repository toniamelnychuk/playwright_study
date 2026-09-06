pipeline {
    agent {docker { image 'mcr.microsoft.com/playwright/java:v1.61.0-noble' }}

    stages {

        stage('Start HTML server') {
            steps {
                sh 'python3 -m http.server 8000 --directory test-page > server.log 2>&1 &'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            sh 'pkill -f "python3 -m http.server 8000" || true'
        }
    }
}