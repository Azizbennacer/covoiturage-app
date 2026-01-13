pipeline {
    agent any
    stages {
        stage('Build & Deploy') {
            steps {
                sh 'mvn -f backend/pom.xml clean install -DskipTests'  
                sh 'docker-compose build'    
                sh 'docker-compose up -d'     
            }
        }
    }
    post {
        always {
            sh 'docker-compose down || true' 
        }
    }
}