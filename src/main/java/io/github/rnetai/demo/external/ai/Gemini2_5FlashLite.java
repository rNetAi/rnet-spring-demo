package io.github.rnetai.demo.external.ai;

import io.github.rNetAi.rnetCore.rNetProtocol.RNetResource;
import io.github.rNetAi.rnetCore.scanner.annotations.Resource;
import org.springframework.stereotype.Component;

@Component
@Resource("gemini-2.5-flash-lite")
public class Gemini2_5FlashLite extends RNetResource {
}
