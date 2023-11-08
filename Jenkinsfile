pipeline {
  agent any
  
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t rahmafeidi/srping:latest spring/'
        sh 'docker build -t rahmafeidi/angular:latest angular/'
      }
    }
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
    stage('Push') {
      steps {
        sh 'docker push rahmafeidi/srping:latest'
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
