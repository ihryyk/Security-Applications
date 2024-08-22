pipeline {
    agent any
    tools{
        maven '3.9.9'
    }
    stages {
        stage('release') {
            steps {
                cd security
                sh 'mvn clean install'
            }
        }
    }
}
