# wanted-pre-onboarding-backend
for wanted-pre-onboarding-backend

---
### 프로젝트 소개

기업의 채용을 위한 간단한 웹 서비스 입니다.
 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

---


#### 요구사항 분석 및 구현 방법
요구사항 분석: 채용공고, 회사, 사용자에 대한 정보가 필요하기 때문에 각각에 대한 엔티티를 만들었습니다. 그리고 JSON을 주고 받는 기능만 수행할 채용공고, 사용자에 대한 DTO를 만들었습니다.

또한 각각의 엔티티에 대한 repository를 생성했습니다

--- 

구현 방법:
1. 채용공고 등록: "/insert" 경로에 post를 통해 사용가능합니다. Recruitment라는 Entity를 등록하기 위해 Insert query가 필요했고 이를 구현하기 위해 JPA의 saveAndFlush기능을 이용했습니다. 데이터는 @ResponseBody를 이용해 JSON으로 받습니다. 
만약에 저장 중 비어있는 필드가 생기거나, 해당 회사의 정보가 존재하지 않는 경우 exception을 발생시켜 처리하도록 만들었습니다.

2. 채용공고 수정: "/update" 경로에 post를 통해 사용가능합니다. RecruitmentDTO의 각각의 요소가 null인지 확인하고 아니라면 해당 필드를 변경할 수 있도록 만들었습니다. 
예외 처리 방식은 등록과 동일하나 구분하기 위해 새로운 exception 클래스를 만들었습니다. 

3. 채용공고 삭제: "/delete/{id}" 경로에 get을 통해 사용가능합니다. @PathVariable을 사용해 id의 값에 따라 해당 id에 해당하는 Recruitment table의 row를 삭제합니다.
 삭제는 JPA의 delete를 사용했습니다.

4. 채용공고 가져오기, 채용공고 검색:  4-1 채용공고 가져오기 "/list" 경로에 get을 통해 사용가능합니다. JPA의 findAll기능을 이용해 모든 채용공고 결과를 return합니다.
4-2 채용공고 검색: "some/url?value={}" 경로에 get을 통해 사용가능합니다. @RequestParam을 이용해 value에는 String값이 들어갈 수 있습니다. 한 번에 모든 요소에 대한 검색을 하기 위해 JpaSpecificationExecutor를 사용했습니다.
Specification을 사용해 findMatchString이라는 method를 구현했고, 이는 country, region, position, detail, techStack에 해당 문자열이 포함되었다면 결과를 반환하도록 만들었습니다. 특히 추가로 Join을 사용해 Company의 name과 일치해도 반환하도록 만들었습니다.

5. 채용 상세 페이지 가져오기: "/list/{id}" 경로에 get을 통해 사용가능합니다. @PathVariable을 사용했습니다. 
해당 채용공고에 접근하면 하나의 결과를 return하며 추가로 회사가 올린 다른 채용공고를 찾을 수 있도록 RecruitmentRepository에 findIdByCompanyId를 구현했습니다.

6. 채용 공고 지원:  "/apply" 경로에 post를 통해 사용가능합니다. 채용공고 등록과 비슷하게 saveAndFlush기능을 이용해 UserService의 applyService method를 구현했습니다.
   

