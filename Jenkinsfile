pipeline {

    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    environment {
        DOCKER_HUB = "mayur240305"
        IMAGE_NAME = "otp-login-app"
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Mayur-2403/otp-login-app.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_HUB/$IMAGE_NAME:v1 .'
            }
        }

        stage('Push Docker Image') {

            steps {

                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'

                    sh 'docker push $DOCKER_HUB/$IMAGE_NAME:v1'
                }
            }
        }
    }

    post {

        success {
            mail to: 'mayur240307@gmail.com',
            subject: 'OTP Login App Build Success',
            body: 'Jenkins Build Successful'
        }

        failure {
            mail to: 'mayur240307@gmail.com',
            subject: 'OTP Login App Build Failed',
            body: 'Jenkins Build Failed'
        }
    }
}
