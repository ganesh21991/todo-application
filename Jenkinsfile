pipeline {
    agent any
    tools {
        maven 'Default'
    }
    stages {
       stage('Build Maven')
        {
            steps {
               checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'githubcred', url: 'https://github.com/ganesh21991/todo-application']]])
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
       // stage('Push Docker Image')
       //  {
       //      steps {
       //        script {
       //         withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {
       //              bat 'docker login -u ganesh21991 -p %dockerhub%'
       //            }
       //           bat 'docker push ganesh21991/todo-application:1.0'
       //        }
       //      }
       //  }
        stage('Push Docker Image') {
          environment {
               registryCredential = 'dockerhub'
           }
         steps{
             script {
                docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                dockerImage.push('ganesh21991/todo-application:1.0')
          }
        }
      }
    }
        stage('Deploy to k8s')
                {
                    steps {
                      script {
                      kubernetesDeploy (configs: 'deployment.yaml')
                      }
                    }
                }

    }
}
