<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main>
    <section class="terms">
        <table>
            <caption>사이트 이용약관</caption>
            <tr>
                <td>
                    <textarea readonly>${termsDto.terms}</textarea>
                    <p>
                        <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                    </p>
                </td>
            </tr>
        </table>
        <table>
            <caption>개인정보 취급방침</caption>
            <tr>
                <td>
                    <textarea readonly>${termsDto.privacy}</textarea>
                    <p>
                        <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                    </p>
                </td>
            </tr>
        </table>
        <div>
            <a href="/jboard/user/login.do" class="btnCancel">취소</a>
            <a href="/jboard/user/register.do" class="btnNext">다음</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>








