pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }

    environment {
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Grant Gradle Wrapper Permission') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew test --no-daemon'
            }
        }
    }

    post {
        always {
            junit testResults: '**/build/test-results/test/*.xml', allowEmptyResults: true

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'build/reports/tests/test',
                    reportFiles: 'index.html',
                    reportName: 'Gradle Test Report'
            ])
        }
        success {
            echo 'Todos os testes passaram com sucesso!'
        }
        failure {
            echo 'Falha na execução dos testes. Verifique o relatório JUnit.'
        }
    }
}