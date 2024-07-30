package blaze;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class BlazeAttributes {
	private final List<BlazeAttribute> attributes; 

	public BlazeAttributes() {
		this.attributes = new ArrayList<>();
	}

	public BlazeAttributes addAttribute(String key, String value) {
		this.attributes.add(new BlazeAttribute(key, value)); 
		return this; 
	}

	public BlazeAttributes addAttribute(BlazeAttribute attribute) {
		this.attributes.add(attribute); 
		return this;
	}

	
	public String render() {
		StringBuilder sb = new StringBuilder(); 
		for (var attr: attributes) {
			sb = sb
				.append(attr.render())
				.append(" "); 
		}
		return sb.toString();
	}
	public BlazeAttribute getFirst() { return this.attributes.getFirst(); }
	public BlazeAttribute getLast() { return this.attributes.getLast(); }
	public BlazeAttributes getSlice(int start, int end) {
		BlazeAttributes self = new BlazeAttributes(); 
		if (start >= 0 && end < self.attributes.size()) {
			for (int i = start; i < end; i++) {
				self.addAttribute(attributes.get(i));
			}
		}
		return self;
	}
	public BlazeAttributes getSlice(int end) {
		BlazeAttributes self = new BlazeAttributes(); 
		if (end < self.attributes.size()) {
			for (int i = 0; i < end; i++) {
				self.addAttribute(attributes.get(i));
			}
		}
		return self;
	}
}
