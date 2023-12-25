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
          docker.withRegistry('', registryCredential) {
            dockerImage.push("1.0")
          }
        }
      }
    }

    /* stage('Deploy to k8s minikube') {
      steps {
        script {
          bat 'kubectl apply -f deployment.yaml'
          timeout(time: 180, unit: 'SECONDS'){
               bat 'minikube service todo-application --url'
          }
        }
      }
    } */
     /* stage('Deploy to k8s openshift') {
          steps {
            script {
              bat 'kubectl apply -f deployment.yaml'
              bat 'kubectl rollout restart deployment/todo-application-deployment'
              timeout(time: 180, unit: 'SECONDS'){
                   bat 'oc get route/todo-application'
              }
            }
          }
        } */
        stage('Deploy to k8s openshift') {
          steps {
                   script {
                        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'OpenShiftLoginCredentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']])
        					{
        						bat 'oc login -u=$USERNAME -p=$PASSWORD'
        					}
                        bat 'kubectl apply -f deployment.yaml'
        				bat 'kubectl rollout restart deployment/todo-application-deployment'
        				timeout(time: 180, unit: 'SECONDS'){
                               bat 'oc get route/todo-application'
        					}
                        }
                }
            }
  }
}
