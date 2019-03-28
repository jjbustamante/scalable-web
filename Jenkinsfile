#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        timestamps()
    }
    tools { 
        maven 'Maven 3.3.9' 
        jdk 'JDK 8' 
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn test"    
            }
        }
    }
}
