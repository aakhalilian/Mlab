package co.test.condition;

import java.util.Date;

import co.miraclelab.webframe.layoutservice.Condition;

public class SimpleCondition implements Condition {

	@Override
	public boolean isTrue() {
		Date date=new Date();
		if(date.getMinutes() %2==0)
		return true;
		else 
			return false;
	}

}
