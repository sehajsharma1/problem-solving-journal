pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }
        stage('deploy') {
           steps {
                sh 'mvn deploy'
            }
        }
    }
}
