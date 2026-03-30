INSERT INTO devices (hostname, ipv4_address, ipv6_address, status, owner, created_at)
VALUES
    ('router-core-01', '192.168.10.1', '2001:db8:10::1', 'IN_SERVICE', 'Network Team', NOW()),
    ('switch-floor-02', '192.168.10.2', '2001:db8:10::2', 'IN_SERVICE', 'IT Support', NOW()),
    ('firewall-edge-01', '192.168.10.254', '2001:db8:10::254', 'IN_SERVICE', 'Security Team', NOW()),
    ('ap-office-03', '192.168.20.10', '2001:db8:20::10', 'MAINTENANCE', 'Facility IT', NOW()),
    ('server-db-01', '192.168.30.15', '2001:db8:30::15', 'IN_SERVICE', 'Backend Team', NOW()),
    ('server-app-02', '192.168.30.16', '2001:db8:30::16', 'IN_SERVICE', 'Backend Team', NOW()),
    ('printer-admin-01', '192.168.40.5', '2001:db8:40::5', 'DECOMMISSIONED', 'Office Admin', NOW()),
    ('vpn-gateway-01', '192.168.50.1', '2001:db8:50::1', 'IN_SERVICE', 'Infrastructure Team', NOW());