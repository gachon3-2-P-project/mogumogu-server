package moguBackend.config.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moguBackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final UserService userService;

    /**
     * 가천대 이메일 인증 코드 전송
     */
    @PostMapping("/emails/verification-requests")
    public ResponseEntity<?> sendMessage(@RequestParam("email") String email) {

        log.info("----");
        userService.sendCodeToEmail(email);

        return ResponseEntity.ok().body(email + "로 메일이 전송되었습니다.");
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity<?> verificationEmail(@RequestParam("email") String email,
                                               @RequestParam("code") String authCode) {
        boolean verificationResult = userService.verifiedCode(email, authCode);

        if (verificationResult) {
            return ResponseEntity.ok().body(email + " 이메일이 성공적으로 인증되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("이메일 인증에 실패했습니다. 올바른 인증 코드를 입력하세요.");
        }
    }
}
