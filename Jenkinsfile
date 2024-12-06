pipeline {

    agent any

    stages {

        stage('start grid') {
            steps {
                sh 'docker compose -f grid.yaml up -d'
            }
        }

        stage('run tests') {
            steps {
                sh 'docker compose -f test-suites.yaml up'
            }
        }

    }

    post {
        always {
            sh 'docker compose -f grid.yaml down'
            sh 'docker compose -f test-suites.yaml down'
            archiveArtifacts artifacts: 'output/automation-tests/index.html', followSymlinks: false
        }
    }

}