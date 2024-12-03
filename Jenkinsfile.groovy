pipeline {

    agent any

    stages {

        stage('run tests') {
            steps {
                sh 'docker compose up'
            }
        }

        stage('grid down') {
            steps {
                sh 'docker compose down'
            }
        }

    }

}
