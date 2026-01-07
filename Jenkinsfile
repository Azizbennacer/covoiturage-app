pipeline {
    agent any
    stages {
        stage('Build & Deploy') {
            steps {
                sh 'mvn -f backend/pom.xml clean install -DskipTests'  // Build JAR
                sh 'docker-compose build'     // Build images
                sh 'docker-compose up -d'     // Lance les conteneurs en arrière-plan
            }
        }
    }
    post {
        always {
            sh 'docker-compose down || true'  // Nettoyage optionnel
        }
    }
}