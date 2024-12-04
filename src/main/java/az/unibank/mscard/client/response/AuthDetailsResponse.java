package az.unibank.mscard.client.response;

import az.unibank.mscard.client.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AuthDetailsResponse {

    private Long userId;
    private String username;
    private Role role;
}
