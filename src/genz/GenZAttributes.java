package genz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hexaredecimal
 */
public class GenZAttributes {
	private final List<GenZAttribute> attributes; 

	public GenZAttributes() {
		this.attributes = new ArrayList<>();
	}

	public GenZAttributes addAttribute(String key, String value) {
		this.attributes.add(new GenZAttribute(key, value)); 
		return this; 
	}

	public GenZAttributes addAttribute(GenZAttribute attribute) {
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
	public GenZAttribute getFirst() { return this.attributes.getFirst(); }
	public GenZAttribute getLast() { return this.attributes.getLast(); }
	public GenZAttributes getSlice(int start, int end) {
		GenZAttributes self = new GenZAttributes(); 
		if (start >= 0 && end < self.attributes.size()) {
			for (int i = start; i < end; i++) {
				self.addAttribute(attributes.get(i));
			}
		}
		return self;
	}
	public GenZAttributes getSlice(int end) {
		GenZAttributes self = new GenZAttributes(); 
		if (end < self.attributes.size()) {
			for (int i = 0; i < end; i++) {
				self.addAttribute(attributes.get(i));
			}
		}
		return self;
	}
}