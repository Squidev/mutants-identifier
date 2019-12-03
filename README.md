# mutants-identifier
Mutants identification service provided by a REST API.

## Services
**Mutants identifier**:  
> Method: POST  
> Endpoint: /mutant/  
> Body:   content-type: application/json  
            content-format: {"dna":["GTCC","CAAG","GGTA","CTAA"]}  
             (The value of "dna" representing a square matrix.)  
> Response:   *HTTP 200-OK* for a positive identification.  
                   *HTTP 403-FORBIDDEN* for a negative identification.  

**Statistics access:**   
> Method: GET  
> Endpoint: /stats  
> Body: none  
> Response:   content-type: application/json  
                   content-format: {"count_mutant_dna": 40, "count_human_dna": 100, "ratio": 0.4}
