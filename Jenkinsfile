pipeline {
    agent any

    parameters {
        choice choices: ['chrome', 'firefox'], description: 'Select the browser', name: 'BROWSER'
        string defaultValue: 'regression', description: 'Input test suite name', name: 'TEST_SUITE'
        choice choices: ['4', '1', '2', '8', '16'], description: 'Select thread count', name: 'THREAD_COUNT'
    }

    stages {
        stage('start grid') {
            steps {
                sh "docker compose -f grid.yaml up --scale ${params.BROWSER}=2 -d"
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
            archiveArtifacts artifacts: 'output/automation-tests/emailable-report.html', followSymlinks: false
        }
    }
}
