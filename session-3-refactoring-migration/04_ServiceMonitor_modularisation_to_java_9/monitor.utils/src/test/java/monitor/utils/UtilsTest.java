package monitor.utils;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class UtilsTest {

	@Test
	void earlySupplierProducesNonEmpty_findFirst_laterSuppliersNotCalled() throws Exception {
		@SuppressWarnings("unchecked")
		Supplier<Optional<String>> notCalled = mock(Supplier.class);
		when(notCalled.get()).thenReturn(Optional.empty());

		Utils.firstPresent(() -> Optional.of("present"), notCalled);

		verifyZeroInteractions(notCalled);
	}

}
