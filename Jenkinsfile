pipeline {
  environment {
    dockerimagename = "ganesh21991/todo-application:1.0"
    dockerImage = ""
  }
  agent any
  tools {
    maven 'Default'
  }
  stages {
    stage('Build Maven') {
      steps {
        checkout([$class: 'GitSCM', branches: [
          [name: '*/master']
        ], extensions: [], userRemoteConfigs: [
          [credentialsId: 'githubcred', url: 'https://github.com/ganesh21991/todo-application']
        ]])
        bat 'mvn clean install'

      }
    }

    stage('Build image') {
      steps {
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }
    stage('Pushing Image') {
      environment {
        registryCredential = 'dockerhub'
      }
      steps {
        script {
          docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
            dockerImage.push("1.0")
          }
        }
      }
    }

    stage('Deploy to k8s') {
      steps {
        script {
          bat 'kubectl apply -f deployment.yaml'
          bat 'minikube service todo-application --url'
        }
      }
    }
  }
}
