pipeline {
    agent any
    tools {
        maven '3.9.9'
    }
    stages {
        stage('release') {
            steps {
                dir('security') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}