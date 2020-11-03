package vtb.app.adapter.persistence.web.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Phone {
    String phoneType;
    String fullPhoneNumber;
}
