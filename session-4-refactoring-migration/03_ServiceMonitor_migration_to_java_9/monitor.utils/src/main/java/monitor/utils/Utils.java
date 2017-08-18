package monitor.utils;

import sun.misc.BASE64Encoder;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Utils {

	public static <T> Stream<T> stream(Optional<T> optional) {
		return optional.map(Stream::of).orElseGet(Stream::empty);
	}

	@SafeVarargs
	public static <T> Optional<T> firstPresent(Supplier<Optional<T>>... optionals) {
		return Arrays.stream(optionals)
				.map(Supplier::get)
				.flatMap(Utils::stream)
				.findFirst();
	}

	public static String toBase64(String content) {
		return new BASE64Encoder().encode(content.getBytes());   // You can see the IDE or the maven compiler is already
		                                                         // complaining about the removal / absence of this class
																 // Find out how we can replace it?
		                                                         // See cheatsheet steps, if you hit a roadblock,
		                                                         // although you might loose out on fun
	}

}
