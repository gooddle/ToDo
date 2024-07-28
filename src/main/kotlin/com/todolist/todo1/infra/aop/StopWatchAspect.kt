//package com.todolist.todo1.infra.aop
//
//import org.aspectj.lang.ProceedingJoinPoint
//import org.aspectj.lang.annotation.Around
//import org.aspectj.lang.annotation.Aspect
//import org.slf4j.LoggerFactory
//import org.springframework.util.StopWatch
//import org.springframework.stereotype.Component
//
//@Aspect
//@Component
//class StopWatchAspect {
//
//    private val logger = LoggerFactory.getLogger("Execution Time Logger")
//
//
//    @Around("@annotation(com.todolist.todo1.infra.aop.StopWatch)")
//    fun run(joinPoint: ProceedingJoinPoint) {
//        val stopWatch = StopWatch()
//
//        stopWatch.start()
//        joinPoint.proceed()
//        stopWatch.stop()
//
//        val timeElapsedMs = stopWatch.totalTimeMillis
//
//        val methodName = joinPoint.signature.name
//        val methodArguments = joinPoint.args
//
//        logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} | Execution Time: ${timeElapsedMs}ms")
//    }
//}