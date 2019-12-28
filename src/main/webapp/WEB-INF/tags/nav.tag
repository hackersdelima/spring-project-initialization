<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav id="menu" class="nav-main" role="navigation">
    <ul class="nav nav-main">
        <li class="nav-active"><a href="<c:url value="/dashboard"/>">
            <i class="fa fa-home" aria-hidden="true"></i> <span>Dashboard</span>
        </a></li>
        <sec:authorize access="hasAuthority('ADMIN')">
        <%--SETTINGS--%>
        <li class="nav-parent"><a> <i class="fa fa-cogs"
                                      aria-hidden="true"></i> <span>Settings</span>
        </a> <!-- office settings -->
            <ul class="nav nav-children">

                <li class="nav-parent"><a><i class="fa fa-briefcase"
                                             aria-hidden="true"></i> Office</a>
                    <ul class="nav nav-children">

                        <li><a href="<c:url value="/office/create-page"/>"><i
                                class="fa fa-edit" aria-hidden="true"></i> Add/Update Office</a></li>
                        <li><a href="<c:url value="/office/view-page"/>"><i
                                class="fa fa-search" aria-hidden="true"></i> View Office</a></li>
                    </ul>
                </li>
            </ul>
            <!-- staff settings -->
            <ul class="nav nav-children">

                <li class="nav-parent"><a><i
                        class="fa fa-users" aria-hidden="true"></i> Staff</a>
                    <ul class="nav nav-children">

                        <li><a href="<c:url value="/staffs/create-page"/>"><i
                                class="fa fa-edit" aria-hidden="true"></i> Add/Update
                            Staff</a></li>
                        <li><a href="<c:url value="/staffs/view-page"/>"><i
                                class="fa fa-search" aria-hidden="true"></i> View
                            Staff</a></li>
                    </ul>
                </li>
            </ul>
            <%-- ./STAFF SETTINGS--%>
            <!-- User settings -->
            <ul class="nav nav-children">
                <li class="nav-parent"><a><i
                        class="fa fa-user" aria-hidden="true"></i> User</a>
                    <ul class="nav nav-children">
                        <li><a href="<c:url value="/users/create-page"/>"><i
                                class="fa fa-edit" aria-hidden="true"></i> Add/Update
                            User</a></li>
                        <li><a href="<c:url value="/users/view-page"/>"><i
                                class="fa fa-search" aria-hidden="true"></i> View User</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <%--./USER SETTINGS--%>
        </li>
        <%--./SETTINGS--%>
        </sec:authorize>
        <%--TRANSACTION--%>
        <li class="nav-parent"><a><i
                class="fa fa-external-link" aria-hidden="true"></i> Transaction</a>
            <ul class="nav nav-children">

                <li><a href="<c:url value="/transactions/create-form"/>"><i
                        class="fa fa-edit" aria-hidden="true"></i> Add
                    Transaction</a></li>
                <li><a href="<c:url value="/transactions/datatable"/>"><i
                        class="fa fa-eye" aria-hidden="true"></i> Show
                    Transaction</a></li>

            </ul>
        </li>
        <%--./TRANSACTION--%>
        <%--STATEMENT--%>
        <li class="nav-parent"><a><i
                class="fa fa-table" aria-hidden="true"></i> Statement</a>
            <ul class="nav nav-children">

                <li><a href="<c:url value="/statements/search-form"/>"><i
                        class="fa fa-search" aria-hidden="true"></i> Search Statement</a></li>

            </ul>
        </li>
        <%--./STATEMENT--%>

    </ul>


</nav>