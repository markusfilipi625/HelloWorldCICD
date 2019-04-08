node {
   stage('SCM checkout'){
       git credentialsId: 'GIT', url: 'https://github.com/markusfilipi625/HelloWorldCICD'
       echo 'Checkout complete'
   }
   
   stage('Mvn Package'){
     def mvnHome = tool name: 'Maven', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     sh "${mvnCMD}  clean package"
     echo 'Mvn Package complete'
   }
   
   stage('Build Docker Image'){
     sh 'docker build -t markus625/helloworldcicd .'
     echo 'Building docker image done'
   }
   
   stage('Push Docker Image'){
     withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerHubPwd')]) {
        sh "docker login -u markus625 -p sarath93"
     }
     sh 'docker push markus625/helloworldcicd'
     echo 'Push Docker Image'
   }
   
   stage('Run Container on Dev Server'){
     def dockerRun = 'docker stop $(docker ps -q --filter ancestor=markus625/helloworldcicd);docker image rm -f markus625/helloworldcicd;docker run -p 8080:8080 -d markus625/helloworldcicd'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.28 ${dockerRun}"
     }
      echo 'Run Container on Dev Server'
   }
}