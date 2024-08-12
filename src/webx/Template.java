package webx;

/**
 *
 * @author hexaredecimal
 */
public class Template extends WebXContainerElement {
	@Override
	public String render() {
		return String.format("<template %s>\n%s</template>", attributes.render(), super.render());
	}
}
