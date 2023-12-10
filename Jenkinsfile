// hi
pipeline {
    agent any
    tools {
        maven 'Default'
    }
    stages {
       stage('Build Maven')
        {
            steps {
               checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'todo-application', url: 'https://github.com/ganesh21991/todo-application']]])
               bat 'mvn clean install'

            }
        }
        stage('Build Docker Image')
        {
            steps {
              script {
                bat 'docker build -t ganesh21991/todo-application:1.0 .'
              }
            }
        }
       stage('Push Docker Image')
        {
            steps {
              script {
               withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                    bat 'docker login -u ganesh21991 -p %dockerhubpwd%'
                  }
                 bat 'docker push ganesh21991/todo-application:1.0'
              }
            }
        }
        stage('Deploy to k8s')
                {
                    steps {
                      script {
                      kubernetesDeploy (configs: 'deployment.yaml', kubeconfigId: 'k8sconfigpwd')
                      }
                    }
                }

    }
}
