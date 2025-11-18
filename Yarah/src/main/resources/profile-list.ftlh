<!-- profile-list.ftlh -->
<!DOCTYPE html>
<html>
<head>
    <title>All Profiles</title>
</head>
<body>
<h1>All Profiles</h1>

<a href="/profiles/new">Create New Profile</a>

<ul>
    <#list profileList as profile>
        <li>
            <a href="/profiles/${profile.profileId}">
                ${profile.firstName!} ${profile.lastName!}
            </a>
            (${profile.accountType})
        </li>
    </#list>
</ul>

</body>
</html>
