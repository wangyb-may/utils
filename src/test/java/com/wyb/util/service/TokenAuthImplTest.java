package com.wyb.util.service;

import com.wyb.util.model.dto.JwtBody;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class TokenAuthImplTest {

	@Test
	void signatureToken() throws NoSuchAlgorithmException {
		//String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIzZjM1NjNhZS02NDBmLTQ3MjEtOThhNy01M2ExYWFjYzBkZmYiLCJ0ZXN0IjoidGVzdE9iaiJ9.DAWhtNAzJyPHQRFmLhRpR8ZI8HVmCDBuGQoWrjYl83pfMrn2BKtwBoWgnBG7MMRubOkAzJIMjhICE89FDDX41RbgdnJjG5gyk2xjB7X4nU-p4sHDuVdolSDdCJepyJPKCVquIY2ZawspVWmsjUb55EK6TzILi92_2EeFwEkbvnjTQVNnHm836ZJX-3D4Gv-awKVeQekw7IaJ5xtdW0pnmuk0JQkGMQWPnRmZ0FMJvX170ZHyonEfqhm16BLL2jFyXBNVf2Xbtd9MCpDUpgP9BdiJUSDwl35zvnp5qQ-alnlpqtw6UaJ9v8ShSqpW5ksv8hp7ggYD0a40EkrpRFA7GQ";
		String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZGF0YSI6eyJuYW1lIjoieHh4IiwicGFzc3dvcmQiOiIxMjEyMTIxMSJ9LCJleHAiOjE2MTE1NjQ5NjV9.X6U4u9u1_H--_jZVuM0D3tPaZaZXYsWjbvrWg56Z-RDQJLYnfEuHIBzBwUiw6VeCdN0qTsZOrdqNyv2LT6dv5r0W_V4Yw5aYOPWuAIyd5WbmsJZyND5iXwupw8CdNGlyLeqYr6suKUloip_vE1hzCPxZkeogXRLyCWatHyIcGStZIYWhfKfnJPETmCXYwb_sTxgAPwCtBNIpJQQThksA8eYfIT4NKJK5X_5PP18A7pu6fuE5ukuI9tPB8AuTCsJAudSgN_0x3bHoKum1qo6F0quRQ5SUhsvgARP8_EVAJWo_zpbykb8obqdm62Pdx4Do3PZfsBE3n_2AFpeIln8Z1g";
		//String token = "sssss";

	}

	@Test
	void authToken() throws NoSuchAlgorithmException{
		String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZGF0YSI6eyJuYW1lIjoieHh4IiwicGFzc3dvcmQiOiIxMjEyMTIxMSJ9LCJleHAiOjE2MTE1NjQ5NjV9.X6U4u9u1_H--_jZVuM0D3tPaZaZXYsWjbvrWg56Z-RDQJLYnfEuHIBzBwUiw6VeCdN0qTsZOrdqNyv2LT6dv5r0W_V4Yw5aYOPWuAIyd5WbmsJZyND5iXwupw8CdNGlyLeqYr6suKUloip_vE1hzCPxZkeogXRLyCWatHyIcGStZIYWhfKfnJPETmCXYwb_sTxgAPwCtBNIpJQQThksA8eYfIT4NKJK5X_5PP18A7pu6fuE5ukuI9tPB8AuTCsJAudSgN_0x3bHoKum1qo6F0quRQ5SUhsvgARP8_EVAJWo_zpbykb8obqdm62Pdx4Do3PZfsBE3n_2AFpeIln8Z1g";
		//String token = "sssss";
		//String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIzZjM1NjNhZS02NDBmLTQ3MjEtOThhNy01M2ExYWFjYzBkZmYiLCJ0ZXN0IjoidGVzdE9iaiJ9.DAWhtNAzJyPHQRFmLhRpR8ZI8HVmCDBuGQoWrjYl83pfMrn2BKtwBoWgnBG7MMRubOkAzJIMjhICE89FDDX41RbgdnJjG5gyk2xjB7X4nU-p4sHDuVdolSDdCJepyJPKCVquIY2ZawspVWmsjUb55EK6TzILi92_2EeFwEkbvnjTQVNnHm836ZJX-3D4Gv-awKVeQekw7IaJ5xtdW0pnmuk0JQkGMQWPnRmZ0FMJvX170ZHyonEfqhm16BLL2jFyXBNVf2Xbtd9MCpDUpgP9BdiJUSDwl35zvnp5qQ-alnlpqtw6UaJ9v8ShSqpW5ksv8hp7ggYD0a40EkrpRFA7GQ";

	}
}