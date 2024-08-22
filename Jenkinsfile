pipeline {
    agent any
    tools{
        maven '3.9.9'
    }
    stages {
        stage('release') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
