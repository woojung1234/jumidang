package com.example.jumidang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/pairing")
public class PairingController {

    @Autowired
    private PairingService pairingService;

    // 페어링 후보 검색
    @PostMapping("/search")
    public ResponseEntity<List<PairingDto>> searchPairings(@RequestBody PairingRequest request) {
        List<PairingDto> results = pairingService.searchPairings(request.getQuery());
        return ResponseEntity.ok(results);
    }

    // 특정 페어링 상세 정보
    @GetMapping("/detail/{id}")
    public ResponseEntity<PairingDetailDto> getPairingDetail(@PathVariable String id) {
        PairingDetailDto detail = pairingService.getPairingDetail(id);
        return ResponseEntity.ok(detail);
    }

    // 특정 노드에서 최대 3-Hop 그래프 조회
    @PostMapping("/graph")
    public ResponseEntity<GraphDto> getGraph(@RequestBody GraphRequest request) {
        GraphDto graph = pairingService.getGraph(request.getNodeId());
        return ResponseEntity.ok(graph);
    }

    // 의미 기반 검색
    @PostMapping("/semantic/search")
    public ResponseEntity<List<PairingDto>> semanticSearch(@RequestBody PairingRequest request) {
        List<PairingDto> results = pairingService.semanticSearch(request.getQuery());
        return ResponseEntity.ok(results);
    }

    // GPT로 자연어 설명 생성
    @PostMapping("/explain")
    public ResponseEntity<ExplainDto> explainPairing(@RequestBody ExplainRequest request) {
        ExplainDto explanation = pairingService.generateExplanation(request.getNodeId1(), request.getNodeId2());
        return ResponseEntity.ok(explanation);
    }
}

// 요청 DTO 예시 클래스
class PairingRequest {
    private String query;

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
}

class GraphRequest {
    private String nodeId;

    public String getNodeId() { return nodeId; }
    public void setNodeId(String nodeId) { this.nodeId = nodeId; }
}

class ExplainRequest {
    private String nodeId1;
    private String nodeId2;

    public String getNodeId1() { return nodeId1; }
    public void setNodeId1(String nodeId1) { this.nodeId1 = nodeId1; }

    public String getNodeId2() { return nodeId2; }
    public void setNodeId2(String nodeId2) { this.nodeId2 = nodeId2; }
}

// 응답 DTO 예시 클래스
class PairingDto {
    private String nodeId;
    private String name;
    private double score;

    // getters & setters
}

class PairingDetailDto {
    private String nodeId;
    private String name;
    private String description;
    private String image;

    // getters & setters
}

class GraphDto {
    private List<NodeDto> nodes;
    private List<EdgeDto> edges;

    // getters & setters
}

class NodeDto {
    private String nodeId;
    private String name;
    private String type;

    // getters & setters
}

class EdgeDto {
    private String source;
    private String target;
    private double weight;

    // getters & setters
}

class ExplainDto {
    private String explanation;

    // getters & setters
}
