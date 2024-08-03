package genz;

/**
 *
 * @author hexaredecimal
 */
public class Template extends GenZContainerElement {
	@Override
	public String render() {
		return String.format("<template %s>\n%s</template>", attributes.render(), super.render());
	}
}
