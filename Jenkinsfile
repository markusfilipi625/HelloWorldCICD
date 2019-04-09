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
   
   stage('Remove Container Image on Dev Server'){
     def dockerRun = 'docker image rm -f markus625/helloworldcicd'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.28 ${dockerRun}"
     }
      echo 'Remove Container Image on Dev Server'
   }
   
   stage('Run Container on Dev Server'){
     def dockerRun = 'docker run -p 8080:8080 -d markus625/helloworldcicd'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.85.28 ${dockerRun}"
     }
      echo 'Run Container on Dev Server'
   }
   
  stage('Deploy on Kubernetes server'){
     def kubeRun = 'sudo kubectl run helloworldcicd --image=markus625/helloworldcicd --port=8080 --replicas=2'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ubuntu@172.31.39.198 ${kubeRun}"
     }
      echo 'Deploy on Kubernetes server'
   }
   
   stage('Expose Kubernetes'){
     def kubeRun = 'sudo kubectl expose deployment.apps/helloworldcicd --type=LoadBalancer --port=8080'
     sshagent(['dev-server']) {
       sh "ssh -o StrictHostKeyChecking=no ubuntu@172.31.39.198 ${kubeRun}"
     }
      echo 'Expose Kubernetes'
   }
}