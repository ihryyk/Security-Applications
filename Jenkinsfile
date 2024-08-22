pipeline {
    agent { label 'linux' }
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