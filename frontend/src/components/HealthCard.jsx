import React, { useEffect } from 'react';

export default function HealthCard({ healthStatus, loading, onRefresh }) {
  // Auto-retry polling when offline
  useEffect(() => {
    if (healthStatus && !healthStatus.success) {
      const timer = setTimeout(() => {
        onRefresh();
      }, 5000);
      return () => clearTimeout(timer);
    }
  }, [healthStatus, onRefresh]);

  const isOnline = healthStatus?.success;
  const data = healthStatus?.data;

  return (
    <div id="health" className="health-monitor-card glass-card">
      <div className="health-header">
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <h3>
            <span>⚙️</span> System Architecture & Backend API Health
          </h3>
          <span className={`status-pill-badge ${isOnline ? 'online' : 'standby'}`} style={{
            padding: '0.2rem 0.6rem',
            borderRadius: '12px',
            fontSize: '0.78rem',
            fontWeight: 600,
            background: isOnline ? 'rgba(16, 185, 129, 0.15)' : 'rgba(245, 158, 11, 0.15)',
            color: isOnline ? '#10B981' : '#F59E0B',
            border: `1px solid ${isOnline ? '#10B981' : '#F59E0B'}`
          }}>
            {loading ? 'Checking...' : isOnline ? '🟢 Live API & DB Synchronized' : '🟡 Standby / Fallback Active'}
          </span>
        </div>

        <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <span className="endpoint-badge">GET /api/health</span>
          <button className="recheck-btn" onClick={onRefresh} disabled={loading}>
            {loading ? 'Testing...' : '🔄 Recheck Connection'}
          </button>
        </div>
      </div>

      <p style={{ color: 'var(--text-muted)', fontSize: '0.92rem', marginBottom: '0.75rem', marginTop: '0.5rem' }}>
        Real-time telemetry from Spring Boot Microservice (Port <code>8081</code>) & H2 Database Engine:
      </p>

      <div className="json-preview" style={{ padding: '1rem', background: 'rgba(0, 0, 0, 0.4)', borderRadius: '8px', border: '1px solid var(--border-color)' }}>
        {loading ? (
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', color: 'var(--accent-cyan)' }}>
            <span className="pulse-loader"></span>
            <span>Querying http://localhost:8081/api/health...</span>
          </div>
        ) : isOnline ? (
          <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.5rem', fontSize: '0.8rem', color: 'var(--text-dim)' }}>
              <span>HTTP Status: <strong>200 OK</strong></span>
              <span>Latency: <strong style={{ color: '#10B981' }}>{healthStatus.latency ?? '< 10'} ms</strong></span>
            </div>
            <pre style={{ margin: 0, fontSize: '0.88rem' }}>
{`{
  "`}<span className="json-key">status</span>{`": "`}<span className="json-string">{data?.status}</span>{`",
  "`}<span className="json-key">message</span>{`": "`}<span className="json-string">{data?.message}</span>{`",
  "`}<span className="json-key">database</span>{`": "`}<span className="json-string">{data?.database || 'Connected'}</span>{`",
  "`}<span className="json-key">version</span>{`": "`}<span className="json-string">{data?.version || '1.0.0'}</span>{`",
  "`}<span className="json-key">timestamp</span>{`": "`}<span className="json-string">{data?.timestamp || new Date().toISOString()}</span>{`"
}`}
            </pre>
          </div>
        ) : (
          <div className="json-standby" style={{ color: 'var(--text-main)', fontSize: '0.88rem' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', color: 'var(--accent-amber)', fontWeight: 600, marginBottom: '0.35rem' }}>
              <span>⚡ Standby Mode — Fallback Advisory Engine Active</span>
            </div>
            <p style={{ margin: 0, color: 'var(--text-muted)', fontSize: '0.84rem', lineHeight: '1.5' }}>
              VyapaarAI is running seamlessly with its integrated deterministic financial engine. To connect live to Spring Boot REST endpoints, run <code>mvn spring-boot:run</code> in the <code>backend/</code> directory.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}

