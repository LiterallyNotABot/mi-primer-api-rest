package com.salmantino.apirest.model.payload;

import lombok.Data;
import lombok.Builder;
import java.io.Serializable;

@Data
@Builder
public class MensajeResponse  implements Serializable {

    private String mensaje;
    private Object object;

}
