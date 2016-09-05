package co.test.context;

import java.util.Date;
import org.apache.velocity.VelocityContext;
import co.miraclelab.webframe.layoutservice.ContextProvider;

public class SimpleContextProvider implements ContextProvider {

	@Override
	public VelocityContext ProvideContext(VelocityContext context) {
		Date date = new Date();
		context.put("hour", date.getHours());
		context.put("min", date.getMinutes());
		return context;
	}

}
