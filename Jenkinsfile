pipeline {
    agent any
    stages {
        stage('Code Checkout') {
            steps {
                checkout scm
                script {
                    env.COMMIT_ID = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    env.BRANCH_NAME = env.BRANCH_NAME ?: sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                    env.IMAGE_TAG = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}-${env.COMMIT_ID}"
                    echo "IMAGE_TAG: ${env.IMAGE_TAG}"
                }
            }
        }
        stage('code build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('build docker image') {
            steps {
                script {
                    def app = docker.build(
                        "sehajsharma07/docker_img_dev:${env.IMAGE_TAG}",
                        "-f Dockerfile ."
                    )
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-cred')  {
                        app.push()
                    }
                }
            }
        }
        stage('deploy to github package') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
