job('ejemplo-job.DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
    scm {
    git('https://github.com/DrBlackSoul/jenkins.job.parametrizado.git', 'main') {
      node / gitConfigName('DrBlackSoul')
      node / gitConfigEmail('matiasnfiordaliso@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Matias', description = 'Parametro de cadena para el job booleano')
    choiceParam('planeta', ['Mercurio', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno']) // Elige el planeta a utilizar en la ejecución del job
    booleanParam('agente', false) // Indica si se debe usar un agente específico para la ejecución del job
  }
  triggers {
    cron('H/7 * * * *')
  }
  steps {
    // Este script ejecuta una serie de comandos para el job
    shell("bash jobscript.sh")
  }
  publishers {
    // Envía un correo electrónico de notificación a matiasnfiordaliso@gmail.com
    mailer('matiasnfiordaliso@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuild()
      notifyUnstable()
      notifyBackToNormal(true)
      notifySuccess()
      notifyRepeatedFailure()
      startNotification()
      includeTestSummary()
      includeCustomMessage()
      customMessage()
      sendAs()
      commitInfoChoice('NONE')
      teamdomain()
      authToken()
    }
  }
}
