pipeline {
    agent any
    stages {
        stage('release') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}