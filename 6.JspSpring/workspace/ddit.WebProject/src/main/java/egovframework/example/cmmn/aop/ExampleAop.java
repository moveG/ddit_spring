package egovframework.example.cmmn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class ExampleAop {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleAop.class);
	/**
	 * 샘플 aop
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 * 
	 * <aop:around pointcut="*" method="selectProc"/>
	 */
	public Object selectProc(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		
		LOGGER.debug("joinPoint : {}", joinPoint);
		LOGGER.debug("joinPoint.getArgs() : {}", joinPoint.getArgs());
		
		Object retValue = null;
		
		try {
			// 필수 : 실행 요청이 된 오펴레이션을 실행(포인트 컷 조건에 해당함)
			stopWatch.start();
			
			// 리스트를 실행하였을 경우
			// sampleService.selectSampleList(searchVO);
			// 가장 중요함, 삭제하면 큰 문제 발생
			// 요청된 서비스 객체의 오퍼레이션을 실행하고 그 처리결과 값을 반환하는 역할 수행
			// retValue : 호출된 메소드가 처리한  결과값
			retValue = joinPoint.proceed();
			LOGGER.debug("retValue : {}", retValue);

			return retValue;

		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();
			
			// 메소드(오퍼레이션) 인자값을 배열로 처리
			Object[] objects = joinPoint.getArgs();
			
			for(Object object : objects) {
				Object map = object;
				
				LOGGER.debug("Object to Map : {}", map);
			}
		}
	}
}
