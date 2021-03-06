/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.logsafe.testing;

import com.palantir.logsafe.SafeLoggable;
import com.palantir.logsafe.exceptions.SafeIllegalArgumentException;
import com.palantir.logsafe.exceptions.SafeIllegalStateException;
import com.palantir.logsafe.exceptions.SafeIoException;
import com.palantir.logsafe.exceptions.SafeNullPointerException;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.CanIgnoreReturnValue;
import org.assertj.core.util.CheckReturnValue;

@CheckReturnValue
public class Assertions extends org.assertj.core.api.Assertions {
    Assertions() {}

    public static LoggableExceptionAssert<SafeIllegalArgumentException> assertThat(
            SafeIllegalArgumentException actual) {
        return LoggableExceptionAssert.create(actual);
    }

    public static LoggableExceptionAssert<SafeIllegalStateException> assertThat(SafeIllegalStateException actual) {
        return LoggableExceptionAssert.create(actual);
    }

    public static LoggableExceptionAssert<SafeNullPointerException> assertThat(SafeNullPointerException actual) {
        return LoggableExceptionAssert.create(actual);
    }

    public static LoggableExceptionAssert<SafeIoException> assertThat(SafeIoException actual) {
        return LoggableExceptionAssert.create(actual);
    }

    public static <T extends Throwable & SafeLoggable> LoggableExceptionAssert<T> assertThatLoggableException(
            T actual) {
        return LoggableExceptionAssert.create(actual);
    }

    @CanIgnoreReturnValue
    public static <T extends Throwable & SafeLoggable> LoggableExceptionAssert<T> assertThatLoggableExceptionThrownBy(
            ThrowingCallable shouldRaiseThrowable) {
        return assertThatThrownBy(shouldRaiseThrowable).asInstanceOf(loggableExceptionAssertFactory());
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable & SafeLoggable>
            InstanceOfAssertFactory<SafeLoggable, LoggableExceptionAssert<T>> loggableExceptionAssertFactory() {
        return new InstanceOfAssertFactory<>(
                SafeLoggable.class, safeLoggable -> LoggableExceptionAssert.create((T) safeLoggable));
    }
}
