
def call() {
    sh """
        trivy image ${env.DOCKER_IMAGE_NAME}:${env.DOCKER_IMAGE_TAG}
    """
}
