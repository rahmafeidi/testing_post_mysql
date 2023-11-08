pipeline {
  agent any
  
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t rahmafeidi/srping spring/'
        sh 'docker build -t rahmafeidi/angular angular/'
      }
    }
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
    stage('Push') {
      steps {
        sh 'docker push rahmafeidi/spring:latest'
        sh 'docker push rahmafeidi/angular:latest'
      }
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}
