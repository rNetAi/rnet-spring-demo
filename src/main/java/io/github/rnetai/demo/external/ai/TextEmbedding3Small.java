package io.github.rnetai.demo.external.ai;

import io.github.rNetAi.rnetCore.rNetProtocol.RNetResource;
import io.github.rNetAi.rnetCore.scanner.annotations.Resource;
import org.springframework.stereotype.Component;

@Component
@Resource("text-embedding-3-small")
public class TextEmbedding3Small extends RNetResource {
}
