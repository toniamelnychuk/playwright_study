pipeline {
    agent {docker { image 'mcr.microsoft.com/playwright/java:v1.61.0-noble' }}

    stages {
        stage('Test') {
            steps {
                sh '''
                    set -eu

                    python3 -m http.server 8000 --directory test-page > server.log 2>&1 &
                    server_pid=$!
                    trap 'kill "$server_pid" 2>/dev/null || true' EXIT INT TERM

                    for attempt in $(seq 1 20); do
                        if curl --fail --silent http://127.0.0.1:8000/login.html > /dev/null; then
                            mvn clean test
                            exit $?
                        fi
                        sleep 1
                    done

                    echo 'The HTML server did not become ready.'
                    cat server.log
                    exit 1
                '''
            }
        }
    }
}
